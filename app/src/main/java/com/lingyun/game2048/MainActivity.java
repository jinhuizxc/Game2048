package com.lingyun.game2048;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tvscore=(TextView) findViewById(R.id.tvScore);
	}


	private TextView tvscore;//显示得分
	private int score=0;	//得分
	private static MainActivity mainActivity=null;//MainActivity类对象，方便其他类调用

	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	public static void setMainActivity(MainActivity mainActivity) {
		MainActivity.mainActivity = mainActivity;
	}
	//构造方法
	public MainActivity(){
		mainActivity=this;
	}
	//清理得分
	public void clearScore(){
		score=0;
	}
	//在TextView显示得分
	public void showScore(){
		tvscore.setText(score+"");
	}
	//加得分，并显示
	public void addScore(int s){
		score+=s;
		showScore();
	}
	//结束时获取总得分
	public int getScore(){
		return score;

	}
}