package link.webarata3.dro.intentservicetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends Fragment implements TestHandler.OnTestHandlerListener {

    private AppCompatTextView textView;

    private OnFragmentInteractionListener onFragmentInteractionListener;

//    Handler handler;

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

        fragment.findViewById(R.id.beginButton).setOnClickListener(view -> {
            if (!ServiceUtil.isServiceRunning(getActivity().getApplicationContext(), TestIntentService.class)) {
                onFragmentInteractionListener.onClickBeginButton();
            }
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
        Intent intent = new Intent(getActivity(), TestIntentService.class);
        intent.putExtra("messenger", new Messenger(new TestHandler(this)));
        getActivity().startService(intent);

    }

    @Override
    public void progressDownload(String status) {
        textView.setText(status);
    }

}

class TestHandler extends Handler {
    private OnTestHandlerListener listener;

    public interface OnTestHandlerListener {
        void progressDownload(String status);
    }

    public TestHandler(OnTestHandlerListener listener) {
        this.listener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d("#########", msg.obj.toString());
        listener.progressDownload(msg.obj.toString());
    }
}
