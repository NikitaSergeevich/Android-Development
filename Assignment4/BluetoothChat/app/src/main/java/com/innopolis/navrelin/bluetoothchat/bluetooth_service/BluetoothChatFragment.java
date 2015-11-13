//package com.innopolis.navrelin.bluetoothchat.bluetooth_service;
//
//import android.bluetooth.BluetoothAdapter;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
///**
// * Created by n.avrelin on 11/13/2015.
// */
//public class BluetoothChatFragment extends Fragment {
//
//    private static final String TAG = "BluetoothChatFragment";
//
//    // Intent request codes
//    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
//    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
//    private static final int REQUEST_ENABLE_BT = 3;
//
//    // Layout Views
//    private ListView mConversationView;
//    private EditText mOutEditText;
//    private Button mSendButton;
//
//    /**
//     * Name of the connected device
//     */
//    private String mConnectedDeviceName = null;
//
//    /**
//     * Array adapter for the conversation thread
//     */
//    private ArrayAdapter<String> mConversationArrayAdapter;
//
//    /**
//     * String buffer for outgoing messages
//     */
//    private StringBuffer mOutStringBuffer;
//
//    /**
//     * Local Bluetooth adapter
//     */
//    private BluetoothAdapter mBluetoothAdapter = null;
//
//    /**
//     * Member object for the chat services
//     */
//    private BluetoothChatService mChatService = null;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//        // Get local Bluetooth adapter
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        // If the adapter is null, then Bluetooth is not supported
//        if (mBluetoothAdapter == null) {
//            FragmentActivity activity = getActivity();
//            Toast.makeText(activity, "Bluetooth is not available", Toast.LENGTH_LONG).show();
//            activity.finish();
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // If BT is not on, request that it be enabled.
//        // setupChat() will then be called during onActivityResult
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//            // Otherwise, setup the chat session
//        } else if (mChatService == null) {
//            setupChat();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mChatService != null) {
//            mChatService.stop();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        // Performing this check in onResume() covers the case in which BT was
//        // not enabled during onStart(), so we were paused to enable it...
//        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
//        if (mChatService != null) {
//            // Only if the state is STATE_NONE, do we know that we haven't started already
//            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
//                // Start the Bluetooth chat services
//                mChatService.start();
//            }
//        }
//    }
//    /**
//     * Set up the UI and background operations for chat.
//     */
//    private void setupChat() {
//        // Initialize the array adapter for the conversation thread
//        mConversationArrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.message);
//
//        mConversationView.setAdapter(mConversationArrayAdapter);
//
//        // Initialize the compose field with a listener for the return key
//        mOutEditText.setOnEditorActionListener(mWriteListener);
//
//        // Initialize the send button with a listener that for click events
//        mSendButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Send a message using content of the edit text widget
//                View view = getView();
//                if (null != view) {
//                    TextView textView = (TextView) view.findViewById(R.id.edit_text_out);
//                    String message = textView.getText().toString();
//                    sendMessage(message);
//                }
//            }
//        });
//
//        // Initialize the BluetoothChatService to perform bluetooth connections
//        mChatService = new BluetoothChatService(getActivity(), mHandler);
//
//        // Initialize the buffer for outgoing messages
//        mOutStringBuffer = new StringBuffer("");
//    }
//
//}
