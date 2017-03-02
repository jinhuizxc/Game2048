package com.lingyun.game2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * @author @凌云小竹（新浪微博）
 *         自定义界面
 */
public class GameView extends GridLayout {


    //构造方法，初始界面
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    //构造方法，初始界面
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    //构造方法，初始界面
    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initGameView();
    }

    //界面初始方法
    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);//bbada0a1debe
        setOnTouchListener(new View.OnTouchListener() {
            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = arg1.getX();
                        startY = arg1.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = arg1.getX() - startX;
                        offsetY = arg1.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth = (Math.min(w, h) - 10) / 4;
        addCards(cardWidth, cardWidth);

        startGame();
    }

    private void addCards(int cardWidth, int cardHeight) {
        Card c;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);
                cardMap[x][y] = c;
            }
        }
    }

    private void startGame() {
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();

    }

    private void addRandomNum() {
        emptyPionts.clear();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardMap[x][y].getNum() <= 0) {
                    emptyPionts.add(new Point(x, y));
                }
            }
        }
        Point p = emptyPionts.remove((int) (Math.random() * emptyPionts.size()));
        cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);


    }

    private void swipeLeft() {
        boolean merg = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardMap[x1][y].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x--;
                            merg = true;
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);

                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merg = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merg) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeRight() {
        boolean merg = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardMap[x1][y].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            merg = true;
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);

                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merg = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merg) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeUp() {
        boolean merg = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardMap[x][y1].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            merg = true;

                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);

                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merg = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merg) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeDown() {
        boolean merg = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardMap[x][y1].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y++;
                            merg = true;
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);

                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merg = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merg) {
            addRandomNum();
            checkComplete();
        }
    }

    public void checkComplete() {
        boolean complete = true;
        All:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardMap[x][y].getNum() == 0 ||
                        (x > 0 && cardMap[x][y].equals(cardMap[x - 1][y])) ||
                        (x < 3 && cardMap[x][y].equals(cardMap[x + 1][y])) ||
                        (y > 0 && cardMap[x][y].equals(cardMap[x][y - 1])) ||
                        (y < 3 && cardMap[x][y].equals(cardMap[x][y + 1]))) {
                    complete = false;
                    break All;
                }
            }
        }
        if (complete) {
            new AlertDialog.Builder(getContext())
                    .setTitle("游戏结束！")
                    .setMessage("得分：" + MainActivity.getMainActivity().getScore())
                    .setPositiveButton("重新挑战", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            startGame();
                        }
                    }).show();
        }
    }

    private Card[][] cardMap = new Card[4][4];
    private List<Point> emptyPionts = new ArrayList<Point>();
}
