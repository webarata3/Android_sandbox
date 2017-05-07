package link.webarata3.dro.intentservicetest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SampleDialogFragment extends DialogFragment {
    private ProgressDialog progressDialog;

    public static SampleDialogFragment newInstance(String title, String message) {
        SampleDialogFragment fragment = new SampleDialogFragment();
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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setCancelable(true);

        return progressDialog;
    }

    @Override
    public void onPause() {
        super.onPause();

        dismiss();
    }
}
