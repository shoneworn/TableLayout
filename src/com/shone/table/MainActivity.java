package com.shone.table;

import java.util.List;

import com.shone.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TableView table ;
	private String[] mlistHead={"日期","类型","金额","操作"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		table = (TableView) findViewById(R.id.table);
		
		table.setTable(6, 3, new OnTableClick() {
			
			@Override
			public void onTableClickListener(int row, int column) {
				Toast.makeText(MainActivity.this,  "row="+row+"   column="+column,Toast.LENGTH_SHORT).show();
			}
		});
		table.setTableHead(mlistHead);
		table.setTableContent();
	}


}
