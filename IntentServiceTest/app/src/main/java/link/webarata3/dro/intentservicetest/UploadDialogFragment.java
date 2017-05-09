package link.webarata3.dro.intentservicetest;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class UploadDialogFragment extends DialogFragment {
    private Dialog dialog;

    public static UploadDialogFragment newInstance(String message, int max) {
        UploadDialogFragment fragment = new UploadDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("progress", 0);
        bundle.putInt("max", max);
        fragment.setArguments(bundle);

        return fragment;
    }

    // http://dev.classmethod.jp/smartphone/android/android-tips-45-custom-dialog/
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (dialog != null) return dialog;

        Log.i("#######" , "## onCreate");
        // パラメータを取得
        String message = getArguments().getString("message");
        int progress = getArguments().getInt("progress");
        int max = getArguments().getInt("max");

        dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_upload);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        AppCompatTextView messageTextView = (AppCompatTextView) dialog.findViewById(R.id.messageTextView);
        messageTextView.setText(message);
        AppCompatTextView progressTextView = (AppCompatTextView) dialog.findViewById(R.id.progressTextView);
        progressTextView.setText(progress + " / " + max);
        dialog.findViewById(R.id.cancelButton).setOnClickListener(v -> dismiss());

        return dialog;
    }

    public void setProgress(int progress) {
        Log.i("#######" , "## setProgress");

        Bundle bundle = getArguments();
        bundle.putInt("progress", progress);

        int max = bundle.getInt("max");
        AppCompatTextView progressTextView = (AppCompatTextView) getDialog().findViewById(R.id.progressTextView);
        progressTextView.setText(progress + " / " + max);
    }
}
