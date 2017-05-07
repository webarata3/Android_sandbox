package link.webarata3.dro.intentservicetest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;
import android.view.WindowManager;

public class UploadDialogFragment extends DialogFragment {
    private ProgressDialog progressDialog;

    public static UploadDialogFragment newInstance(String title, String message) {
        UploadDialogFragment fragment = new UploadDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // パラメータを取得
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");

        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_upload);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);

        dialog.findViewById(R.id.cancelButton).setOnClickListener(v -> dismiss());

        return dialog;
    }

    @Override
    public void onPause() {
        super.onPause();

        dismiss();
    }
}
