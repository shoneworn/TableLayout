package com.shone.table;

import java.util.List;

import com.shone.androidtest.R;

/**
 * 
 */

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author docking
 *
 */
public class TableView extends RelativeLayout {

	public final static int LEFT_VISIBLE = 1;
	public final static int RIGHT_VISIBLE = 2;
	public final static int ALL_VISIBLE = 3;
	public final static int LEFT_RIGHT_VISIBLE = 4;

	private Context mContext = null;
	private TableLayout mtable = null;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;

	// private List<String> mlistHead = null;
	private int rows, columns;

	private OnTableClick mOnTableClick;

	public TableView(Context context) {
		super(context);
		this.mContext = context;
	}

	public TableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
	}

	public TableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
	}

	/**
	 * 设置行数和列数
	 * 
	 * @param rows
	 * @param columns
	 */
	public void setTable(int rows, int columns, OnTableClick mOnTableClick) {
		this.rows = rows;
		this.columns = columns;
		this.mOnTableClick = mOnTableClick;
	}

	/**
	 * 设置表头
	 */
	public void setTableHead(String[] mlistHead) {
		// this.mlistHead = mlistHead;
		initHead(mlistHead);
	}

	/**
	 * 设置表格
	 */
	public void setTableContent() {
		init();
	}

	/**
	 * 初始化表头
	 */
	private void initHead(String[] mlistHead) {
		LayoutInflater.from(mContext).inflate(R.layout.tableview, this);
		mtable = (TableLayout) this.findViewById(R.id.table_head);
		mtable.setStretchAllColumns(true);
		TableRow mTableRow = new TableRow(mContext);
		mTableRow.setBackgroundColor(Color.rgb(255, 255, 255));
		for (int col = 0; col < columns; col++) {
			TextView mColumn = new TextView(mContext);
			mColumn.setHeight(100);
			mColumn.setBackgroundResource(R.drawable.shapee_head);
			mColumn.setGravity(Gravity.CENTER);
			mColumn.setTextColor(Color.rgb(255, 255, 255));
			if(mlistHead.length>col)
			mColumn.setText(mlistHead[col]);
			mTableRow.addView(mColumn, col);
		}
		mtable.addView(mTableRow, new TableLayout.LayoutParams(WC, FP));
	}

	/**
	 * 初始化表格
	 */
	private void init() {
		LayoutInflater.from(mContext).inflate(R.layout.tableview, this);
		mtable = (TableLayout) this.findViewById(R.id.table_content);
		mtable.setStretchAllColumns(true);
		for (int row = 0; row < rows; row++) {
			TableRow mTableRow = new TableRow(mContext);
			mTableRow.setBackgroundColor(Color.rgb(255, 255, 255));
			for (int col = 0; col < columns; col++) {
				TextView mColumn = new TextView(mContext);
				mColumn.setHeight(100);
				if (col < columns - 1) {
					mColumn.setBackgroundResource(R.drawable.shapee_left);
				} else {
					mColumn.setBackgroundResource(R.drawable.shapee_right);
				}
				mTableRow.addView(mColumn, col);
				// 方法一：
				// mTableRow.getChildAt(col).setOnClickListener(new
				// myListener(row, col, mOnTableClick));
				// 方法二：
				mColumn.setOnClickListener(new myListener(row, col, mOnTableClick));
				// 这里让自己头疼了很久，因为开始不知道怎样设置某一列的点击事件，汗。。。设置点击监听后，如歌用匿名内部类的话，
				// 因为匿名内部类中取不到外部类的非静态变量的值，所以采用自定义的监听类
				// 记得要自定义一个构造函数，并定义参数row ,col
				// ，这样好把这里的i的值传入到myListener中的去，在同回掉接口，回调给主界面

			}
			// 方法三：在这里可以直接指定具体那一列受到监听
			// mTableRow.getChildAt(3).setOnClickListener(new myListener(row,
			// mOnTableClick));
			mtable.addView(mTableRow, new TableLayout.LayoutParams(WC, FP));
		}
	}

}

class myListener implements OnClickListener {

	int col = 0;
	int row = 0;
	OnTableClick mOnTableClick;

	public myListener(int row, int col, OnTableClick mOnTableClick) {
		super();
		this.row = row;
		this.col = col;
		this.mOnTableClick = mOnTableClick;
	}

	@Override
	public void onClick(View v) {
		mOnTableClick.onTableClickListener(row, col);
	}
}

interface OnTableClick {
	public abstract void onTableClickListener(int row, int col);
}