package com.lease;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.CarModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;
import com.pony.view.StarBar;

public class ReviewCreatActivity extends BaseActivity {
	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mSubmit;

	private EditText interestMessage;
	private StarBar starBar;
	
	private CarModel storeModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_review);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;

		case R.id.mSubmit:
			if (TextUtils.isEmpty(interestMessage.getText().toString())) {
				ToastUtil.ShowCentre(ReviewCreatActivity.this, "请输入信息");
				return;
			}
			createTopicPost(true);

			break;

		}
	}

	@Override
	public void initWidget() {
		storeModel = (CarModel) this.getIntent().getSerializableExtra("msg");
		interestMessage = (EditText) findViewById(R.id.interestMessage);
		starBar = (StarBar) findViewById(R.id.starBar);
		mSubmit = (Button) findViewById(R.id.mSubmit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("服务评价");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mSubmit.setOnClickListener(this);

	}

	@Override
	public void initData() {
		
		
		
	
	}
	private void createTopicPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addReviewMessage");
		params.put("reviewUserId", MemberUserUtils.getUid(this));
		params.put("reviewUserName", MemberUserUtils.getName(this));
		params.put("rReviewContent", interestMessage.getText().toString());
		params.put("rMessageId", storeModel.getCarId());
		httpPost(Consts.URL + Consts.APP.ReviewAction, params, Consts.actionId.resultCode, isShow, "正在注册...");

	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(ReviewCreatActivity.this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
			}
		}, 2000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(ReviewCreatActivity.this, strMsg);

	}
}
