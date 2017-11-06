package ciprian.saver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ciprian Anton on 11-06-2017.
 */
public class SplashScreen extends Activity {

    private boolean backbtnPress;
    ImageView img;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_from_left);

        img = (ImageView)findViewById(R.id.splashScreenImage);
        img.setAnimation(anim);

        textView = (TextView) findViewById(R.id.splashAppName);
        textView.setAnimation(anim1);

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {


                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(intent);

                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        backbtnPress = true;
        super.onBackPressed();
    }

}

