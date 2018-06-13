package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lease.R;
import com.pony.config.Consts;
import com.pony.model.CollectModel;
import com.squareup.picasso.Picasso;

public class MyCollectListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<CollectModel> list_result;
	private int posIndex;
	private Context mContext;

	public MyCollectListAdapter(Context context, List<CollectModel> list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.look_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoney);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mivShop = (ImageView) convertView.findViewById(R.id.mivShop);
			holder.mTvState = (TextView) convertView.findViewById(R.id.mTvState);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// try {
		//
		// if(list_result.get(position).getStateMessage().equals("true")){
		// holder.mTvState.setText("(已预订)");
		// holder.mTvState.setTextColor(Color.parseColor("#ff0000"));
		// }else{
		// holder.mTvState.setText("(可预订)");
		// holder.mTvState.setTextColor(Color.parseColor("#458B74"));
		// }
		holder.mTvTitle.setText(list_result.get(position).getCarMsg().getCarNo());
		holder.mTvMoney.setText("订金金额：" + list_result.get(position).getCarMsg().getCarMoney() + "元/天");


		holder.mTvState.setText("(" + list_result.get(position).getCarMsg().getCarTypeName() + ")");

		holder.mtvTime.setText("发布时间：" + list_result.get(position).getCarMsg().getCarTime().substring(0, 10) );

		if (!TextUtils.isEmpty(list_result.get(position).getCarMsg().getCarImage())) {
			Picasso.with(mContext).load(Consts.URL_IMAGE_LOCAL + list_result.get(position).getCarMsg().getCarImage())
					.placeholder(R.drawable.default_drawable_show_pictrue).into(holder.mivShop);
		}

		//

		//

		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
		private TextView mTvMoney;
		private TextView mtvTime;
		private TextView mTvState;
		private ImageView mivShop;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
