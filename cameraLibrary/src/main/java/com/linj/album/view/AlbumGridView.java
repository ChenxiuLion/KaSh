package com.linj.album.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.linj.FileOperateUtil;
import com.linj.cameralibrary.R;
import com.linj.imageloader.DisplayImageOptions;
import com.linj.imageloader.ImageLoader;
import com.linj.imageloader.displayer.RoundedBitmapDisplayer;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;

/**
 * @ClassName: AlbumView
 * @Description:  ���View���̳���GridView����װ��Adapter��ͼƬ���ط���
 * @author LinJ
 * @date 2015-1-5 ����5:09:08
 *
 */
public class AlbumGridView extends GridView{
	public final static String TAG="AlbumView";
	/**  ͼƬ������ �Ż����˻���  */ 
	private ImageLoader mImageLoader;
	/**  ����ͼƬ���ò��� */ 
	private DisplayImageOptions mOptions;	
	/**  ��ǰ�Ƿ��ڱ༭״̬ trueΪ�༭ */ 
	private boolean mEditable;

	public AlbumGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mImageLoader= ImageLoader.getInstance(context);
		//��������ͼƬ���ز���
		DisplayImageOptions.Builder builder= new DisplayImageOptions.Builder();
		builder =builder
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageOnFail(R.drawable.ic_error)
				.cacheInMemory(true)
				.cacheOnDisk(false)
				.displayer(new RoundedBitmapDisplayer(20));
		mOptions=builder.build();
		setBackgroundColor(Color.WHITE);
		//���ش�ֱ������
		setVerticalScrollBarEnabled(false);
	}


	
	/**  
	 *  ȫѡͼƬ
	 *  @param listener ѡ��ͼƬ��ִ�еĻص�����   
	 */
	public void selectAll(AlbumGridView.OnCheckedChangeListener listener){
		((AlbumViewAdapter)getAdapter()).selectAll(listener);
	}
	/**  
	 * ȡ��ȫѡͼƬ
	 *  @param listener   ѡ��ͼƬ��ִ�еĻص�����  
	 */
	public void unSelectAll(AlbumGridView.OnCheckedChangeListener listener){
		((AlbumViewAdapter)getAdapter()).unSelectAll(listener);
	}

	/**  
	 * ���ÿɱ༭״̬
	 *  @param editable �Ƿ�ɱ༭   
	 */
	public void setEditable(boolean editable){
		mEditable=editable;
		((AlbumViewAdapter)getAdapter()).notifyDataSetChanged(null);
	}
	/**  
	 * ���ÿɱ༭״̬
	 *  @param editable �Ƿ�ɱ༭   
	 *  @param listener ѡ��ͼƬ��ִ�еĻص�����  
	 */
	public void setEditable(boolean editable,AlbumGridView.OnCheckedChangeListener listener){
		mEditable=editable;
		((AlbumViewAdapter)getAdapter()).notifyDataSetChanged(listener);
	}

	/**  
	 *  ��ȡ�ɱ༭״̬
	 *  @return   
	 */
	public boolean getEditable(){
		return mEditable;
	}

	/**  
	 *  ��ȡ��ǰѡ���ͼƬ·������
	 *  @return   
	 */
	public Set<String> getSelectedItems(){
		return ((AlbumViewAdapter)getAdapter()).getSelectedItems();
	}

	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		((AlbumViewAdapter)getAdapter()).notifyDataSetChanged();
	}

	/** 
	 * @ClassName: OnCheckedChangeListener 
	 * @Description:  ͼƬѡ�к�ļ����ӿڣ�������activity�����ص�����
	 * @author LinJ
	 * @date 2015-1-5 ����5:13:43 
	 *  
	 */
	public interface OnCheckedChangeListener{
		public void onCheckedChanged(Set<String> set);
	}


	/** 
	 * @ClassName: AlbumViewAdapter 
	 * @Description:  ���GridView������
	 * @author LinJ
	 * @date 2015-1-5 ����5:14:14 
	 *  
	 */
	public class AlbumViewAdapter extends BaseAdapter implements OnClickListener,CompoundButton.OnCheckedChangeListener
	{

		/** ���ص��ļ�·������ */ 
		List<String> mPaths;

		/**  ��ǰѡ�е��ļ��ļ��� */ 
		Set<String> itemSelectedSet=new HashSet<String>();

		/**  ѡ��ͼƬ��ִ�еĻص����� */ 
		AlbumGridView.OnCheckedChangeListener listener=null;



		public AlbumViewAdapter(List<String> paths) {
			super();
			this.mPaths = paths;
		}
		/**  
		 * ���������ݸı�ʱ�����»���
		 *  @param listener   
		 */
		public void notifyDataSetChanged(AlbumGridView.OnCheckedChangeListener listener) {
			//����map
			itemSelectedSet=new HashSet<String>();
			this.listener=listener;
			super.notifyDataSetChanged();
		}
		/**  
		 * ѡ�������ļ�
		 *  @param listener   
		 */
		public void selectAll(AlbumGridView.OnCheckedChangeListener listener){
			for (String path : mPaths) {
				itemSelectedSet.add(path);
			}
			this.listener=listener;
			super.notifyDataSetChanged();
			if(listener!=null) listener.onCheckedChanged(itemSelectedSet);
		}

		/**  
		 *  ȡ��ѡ�������ļ�
		 *  @param listener   
		 */
		public void unSelectAll(AlbumGridView.OnCheckedChangeListener listener){
			notifyDataSetChanged(listener);
			if(listener!=null) listener.onCheckedChanged(itemSelectedSet);
		}
		/**  
		 * ��ȡ��ǰѡ���ļ��ļ���
		 *  @return   
		 */
		public Set<String> getSelectedItems(){
			return itemSelectedSet;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPaths.size();
		}


		@Override
		public String getItem(int position) {
			// TODO Auto-generated method stub
			return mPaths.get(position);
		}


		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ThumbnaiImageView albumItemView = (ThumbnaiImageView)convertView;
			if (albumItemView == null) albumItemView = new ThumbnaiImageView(getContext(),mImageLoader,mOptions);
			albumItemView.setOnCheckedChangeListener(this);
			//���õ���¼�����ItemClick�¼�ת��ΪAlbumItemView��Click�¼�
			albumItemView.setOnClickListener(this);
			String path=getItem(position);
			albumItemView.setTags(path,position, mEditable, itemSelectedSet.contains(path));
			return albumItemView;
		}

		@Override
		public void onClick(View v) {
			if(getOnItemClickListener()!=null){
				//����ȡ�������㸸�࣬��Ϊ������onClick����FilterImageView
				ThumbnaiImageView view=(ThumbnaiImageView)v.getParent().getParent();
				getOnItemClickListener().onItemClick(AlbumGridView.this, view, view.getPosition(), 0L);
			}
		}
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if(buttonView.getTag()==null) return;
			if (isChecked) itemSelectedSet.add(buttonView.getTag().toString());
			else itemSelectedSet.remove(buttonView.getTag().toString());
			if(listener!=null) listener.onCheckedChanged(itemSelectedSet);
		}
	}
}
