package link.webarata3.dro.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultActivityFragment extends Fragment {

    private AppCompatTextView textView;

    public ResultActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        textView = (AppCompatTextView) view.findViewById(R.id.textView);
        return view;
    }

    public void load(int x, int y) {
        textView.setText(String.valueOf(x + y));
    }
}
