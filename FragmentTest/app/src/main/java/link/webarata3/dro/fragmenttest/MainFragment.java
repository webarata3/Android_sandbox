package link.webarata3.dro.fragmenttest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
    public interface OnMainFragmentListener {
        void onClickButton(int x, int y);
    }

    private OnMainFragmentListener onMainFragmentListener;

    private TextInputLayout textInputLayout1;
    private AppCompatEditText editText1;
    private AppCompatEditText editText2;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        textInputLayout1 = (TextInputLayout) fragment.findViewById(R.id.textInputLayout1);
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

    private void onAttachContext(Context context) {
        if (context instanceof OnMainFragmentListener) {
            onMainFragmentListener = (OnMainFragmentListener) context;
        } else {
            throw new RuntimeException("ActivityにOnMainActivityの実装が必要です");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onMainFragmentListener = null;
    }

    private void checkAndExec() {
        boolean isError = false;
        if (TextUtils.isEmpty(editText1.getText())) {
            textInputLayout1.setErrorEnabled(true);
            textInputLayout1.setError(getString(R.string.message_input_number));
            isError = true;
        } else {
            textInputLayout1.setErrorEnabled(false);
            textInputLayout1.setError(null);
        }
        if (TextUtils.isEmpty(editText2.getText())) {
            editText2.setError(getString(R.string.message_input_number));
            isError = true;
        } else {
            editText2.setError(null);
        }
        if (isError) return;

        int x = Integer.parseInt(editText1.getText().toString());
        int y = Integer.parseInt(editText2.getText().toString());
        onMainFragmentListener.onClickButton(x, y);
    }
}
