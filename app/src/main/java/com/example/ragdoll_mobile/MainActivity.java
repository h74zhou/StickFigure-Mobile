package com.example.ragdoll_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawingCanvas canvas = new DrawingCanvas(this);
        canvas.addSprite(this.makeSprite());
        setContentView(canvas);
    }

    private static Sprite makeSprite() {
        Sprite firstSprite = new RectangleSprite(250, 450);
        return firstSprite;
    }

}
