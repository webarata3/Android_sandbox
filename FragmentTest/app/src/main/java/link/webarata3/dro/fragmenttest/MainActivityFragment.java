package link.webarata3.dro.fragmenttest;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment {
    public interface OnMainActivityFragmentListener {
        void onClickButton(int x, int y);
    }

    private OnMainActivityFragmentListener onMainActivityFragmentListener;

    private AppCompatEditText editText1;
    private AppCompatEditText editText2;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        editText1 = (AppCompatEditText) fragment.findViewById(R.id.editText1);
        editText2 = (AppCompatEditText) fragment.findViewById(R.id.editText2);
        fragment.findViewById(R.id.button).setOnClickListener(view -> {
            checkAndExec();
        });

        return fragment;
    }

    // http://qiita.com/syarihu/items/a8bb189d33585238c287
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachContext(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) return;
        onAttachContext(activity);
    }

    private void onAttachContext(Context context) {
        if (context instanceof OnMainActivityFragmentListener) {
            onMainActivityFragmentListener = (OnMainActivityFragmentListener) context;
        } else {
            throw new RuntimeException("AcitityにOnMainActivityの実装が必要です");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onMainActivityFragmentListener = null;
    }

    private void checkAndExec() {
        boolean isError = false;
        if (TextUtils.isEmpty(editText1.getText())) {
            editText1.setError(getString(R.string.message_input_number));
            isError = true;
        }
        if (TextUtils.isEmpty(editText2.getText())) {
            editText2.setError(getString(R.string.message_input_number));
            isError = true;
        }
        if (isError) return;

        int x = Integer.parseInt(editText1.getText().toString());
        int y = Integer.parseInt(editText2.getText().toString());
        onMainActivityFragmentListener.onClickButton(x, y);
    }
}
