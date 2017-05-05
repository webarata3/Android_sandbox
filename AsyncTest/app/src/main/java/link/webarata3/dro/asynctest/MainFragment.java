package link.webarata3.dro.asynctest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
    private AppCompatTextView textView;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (AppCompatTextView) fragment.findViewById(R.id.textView);

        fragment.findViewById(R.id.beginButton).setOnClickListener(view -> {
            onFragmentInteractionListener.onClickBeginButton();
        });

        return fragment;
    }

    // http://qiita.com/syarihu/items/a8bb189d33585238c287
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    public void viewDownloadStatus(int test) {
        textView.setText(String.valueOf(test));
    }

    public interface OnFragmentInteractionListener {
        void onClickBeginButton();
    }
}
