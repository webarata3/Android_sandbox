package link.webarata3.dro.intentservicetest2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivityFragment extends Fragment implements TestResultReceiver.Receiver {
    private AppCompatTextView textView;
    private TestResultReceiver receiver;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    public interface OnFragmentInteractionListener {
        void onClickBeginButton();
    }

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        textView = (AppCompatTextView) fragment.findViewById(R.id.textView);

        receiver = new TestResultReceiver(new Handler());
        receiver.setReceiver(this);

        fragment.findViewById(R.id.beginButton).setOnClickListener(view -> {
            onFragmentInteractionListener.onClickBeginButton();
        });

        return fragment;
    }

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

    public void onClickBeginButton() {
        Toast.makeText(getActivity(), "Start", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), TestIntentService.class);
        intent.putExtra("receiver", receiver);

        getActivity().startService(intent);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        textView.setText(resultData.getString("progress"));
    }
}
