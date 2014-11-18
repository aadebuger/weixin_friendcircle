package com.example.weixin_friendcircle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.weixin_friendcircle.TitlePopup.OnItemOnClickListener;

public class MainActivity extends Activity implements OnItemOnClickListener {

	private ImageView btn1, btn2, btn3;
	private TitlePopup titlePopup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (ImageView) findViewById(R.id.button1);
		btn1.setOnClickListener(onclick);
		btn2 = (ImageView) findViewById(R.id.button2);
		btn2.setOnClickListener(onclick);
		btn3 = (ImageView) findViewById(R.id.button3);
		btn3.setOnClickListener(onclick);
		titlePopup = new TitlePopup(this, Util.dip2px(this, 165), Util.dip2px(
				this, 40));
		titlePopup
				.addAction(new ActionItem(this, "ÔÞ", R.drawable.circle_praise));
		titlePopup.addAction(new ActionItem(this, "ÆÀÂÛ",
				R.drawable.circle_comment));
		titlePopup.setItemOnClickListener(this);
	}

	OnClickListener onclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			titlePopup.setAnimationStyle(R.style.cricleBottomAnimation);
			titlePopup.show(v);
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public void onItemClick(com.example.weixin_friendcircle.ActionItem item,
			int position) {
		// TODO Auto-generated method stub

	}

}
