package com.lingyun.game2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * @author @凌云小竹（新浪微博）
 *         卡片类
 */
public class Card extends FrameLayout {
    private TextView lable;

    public Card(Context context) {
        super(context);
        lable = new TextView(getContext());
        lable.setTextSize(35);
        lable.setTextColor(0xffffffff);
        lable.setBackgroundColor(0x33ffffff);//33表示不透明度，00 表示完全透明，ff 表示完全不透明
        lable.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        //lp.setMargins(left, top, right, bottom)
        addView(lable, lp);

        setNum(0);
    }

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            lable.setText("");
        } else {
            lable.setText(num + "");
        }
    }
//	public void setColor(){
//		if (num>0) {
//			setBackgroundColor(Color.RED);
//		}
//	}

    public boolean equals(Card c) {

        return getNum() == c.getNum();
    }

}

