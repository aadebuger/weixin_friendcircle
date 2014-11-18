package com.example.weixin_friendcircle;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author yangyu �������������ⰴť�ϵĵ������̳���PopupWindow��
 */
public class TitlePopup extends PopupWindow {

	private TextView priase;
	private TextView comment;

	private Context mContext;

	// �б����ļ��
	protected final int LIST_PADDING = 10;

	// ʵ����һ������
	private Rect mRect = new Rect();

	// �����λ�ã�x��y��
	private final int[] mLocation = new int[2];

	// ��Ļ�Ŀ�Ⱥ͸߶�
	private int mScreenWidth, mScreenHeight;

	// �ж��Ƿ���Ҫ��ӻ�����б�������
	private boolean mIsDirty;

	// λ�ò�������
	private int popupGravity = Gravity.NO_GRAVITY;

	// ����������ѡ��ʱ�ļ���
	private OnItemOnClickListener mItemOnClickListener;

	// ���嵯���������б�
	private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

	public TitlePopup(Context context) {
		// ���ò��ֵĲ���
		this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	public TitlePopup(Context context, int width, int height) {
		this.mContext = context;

		// ���ÿ��Ի�ý���
		setFocusable(true);
		// ���õ����ڿɵ��
		setTouchable(true);
		// ���õ�����ɵ��
		setOutsideTouchable(true);

		// �����Ļ�Ŀ�Ⱥ͸߶�
//		mScreenWidth = Util.getScreenWidth(mContext);
//		mScreenHeight = Util.getScreenHeight(mContext);

		// ���õ����Ŀ�Ⱥ͸߶�
		setWidth(width);
		setHeight(height);

		setBackgroundDrawable(new BitmapDrawable());

		// ���õ����Ĳ��ֽ���
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.comment_popu, null);
		setContentView(view);
		Log.e("",
				"3333==========" + view.getHeight() + "    " + view.getWidth());
		priase = (TextView) view.findViewById(R.id.popu_praise);
		comment = (TextView) view.findViewById(R.id.popu_comment);
		priase.setOnClickListener(onclick);
		comment.setOnClickListener(onclick);
	}

	/**
	 * ��ʾ�����б����
	 */
	public void show(final View c) {
		// ��õ����Ļ��λ������
		c.getLocationOnScreen(mLocation);
		// ���þ��εĴ�С
		mRect.set(mLocation[0], mLocation[1], mLocation[0] + c.getWidth(),
				mLocation[1] + c.getHeight());
		priase.setText(mActionItems.get(0).mTitle);
		// �ж��Ƿ���Ҫ��ӻ�����б�������
		if (mIsDirty) {
			// populateActions();
		}
		Log.e("", "333  " + this.getHeight());// 50
		Log.e("", "333  " + c.getHeight());// 96
		Log.e("", "333  " + this.getWidth());

		Log.e("", "333  " + (mLocation[1]));

		// ��ʾ������λ��
		// showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING
		// - (getWidth() / 2), mRect.bottom);
		showAtLocation(c, Gravity.NO_GRAVITY, mLocation[0] - this.getWidth()
				- 10, mLocation[1] - ((this.getHeight() - c.getHeight()) / 2));
	}

	OnClickListener onclick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			dismiss();
			switch (v.getId()) {
			case R.id.popu_comment:
				mItemOnClickListener.onItemClick(mActionItems.get(1), 1);
				break;
			case R.id.popu_praise:
				mItemOnClickListener.onItemClick(mActionItems.get(0), 0);
				break;
			}
		}

	};

	/**
	 * ���������
	 */
	public void addAction(ActionItem action) {
		if (action != null) {
			mActionItems.add(action);
			mIsDirty = true;
		}
	}

	/**
	 * ���������
	 */
	public void cleanAction() {
		if (mActionItems.isEmpty()) {
			mActionItems.clear();
			mIsDirty = true;
		}
	}

	/**
	 * ����λ�õõ�������
	 */
	public ActionItem getAction(int position) {
		if (position < 0 || position > mActionItems.size())
			return null;
		return mActionItems.get(position);
	}

	/**
	 * ���ü����¼�
	 */
	public void setItemOnClickListener(
			OnItemOnClickListener onItemOnClickListener) {
		this.mItemOnClickListener = onItemOnClickListener;
	}

	/**
	 * @author yangyu �������������������ť�����¼�
	 */
	public static interface OnItemOnClickListener {
		public void onItemClick(ActionItem item, int position);
	}
}
