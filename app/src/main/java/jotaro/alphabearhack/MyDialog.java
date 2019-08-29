package jotaro.alphabearhack;
import jotaro.alphabearhack.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class MyDialog extends Activity {
	public static boolean active = false;
	public static Activity myDialog;
	
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dialog);
		
		btn = (Button) findViewById(R.id.dialog_btn);

		myDialog = MyDialog.this;
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
	}
		
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		active = true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		active = false;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		active = false;
	}
	
}
