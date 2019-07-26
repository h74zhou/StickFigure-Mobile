package com.example.ragdoll_mobile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

public class RectangleSprite extends Sprite {
    private RectF rect = null;

    public RectangleSprite(int width, int height) {
        super();
        this.initialize(width,height);
    }

    public void initialize(int width, int height) {
        rect = new RectF(700, 500, 700+width, 500+height);
    }

    public boolean pointInside(PointF p) {

        Matrix fullTransform = this.getFullTransform();
        Matrix inverseTransform = new Matrix();

        if (fullTransform.invert(inverseTransform)) {
            // Everything is Good
            Log.d("herun: ", "Successfully Inverted");
        } else {
            Log.d("herun: ", "Failed to Invert");
        }

        PointF newPoint = new PointF(p.x, p.y);
        float[] pts = new float[2];
        pts[0] = newPoint.x;
        pts[1] = newPoint.y;
//        inverseTransform.transform(newPoint, newPoint);
        inverseTransform.mapPoints(pts);
        newPoint.x = pts[0];
        newPoint.y = pts[1];
//        Log.d("herun: ", "Mouse X Point (TRANSFORMED): " + newPoint.x);
//        Log.d("herun: ", "Mouse Y Point (TRANSFORMED): " + newPoint.y);

        if (rect.contains(newPoint.x, newPoint.y)) {
            Log.d("herun: ", "THIS INVERSION WORKEDDDDD!");
        } else {
            Log.d("herun: ", "THIS INVERSION DID NOT WORK!!!");
        }
        return rect.contains(newPoint.x, newPoint.y);
    }

    public void drawSprite(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(rect, 20.0f, 20.0f, paint);
    }

    public String toString() {
        return "RectangleSprite: " + rect;
    }

}
