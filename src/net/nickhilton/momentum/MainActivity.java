package net.nickhilton.momentum;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.widget.ScrollView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ScrollView s = (ScrollView) this.findViewById(R.id.scrollViewContent);
		
		Scroller scroller = new Scroller(s,5);
		
		Thread scrollerController = new Thread(scroller);
		
		scrollerController.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	class Scroller implements Runnable {
		private ScrollView _scrollView;
		private int _baseSpeed = 5;
		private boolean _dispose = false;
		
		public Scroller(ScrollView scrollView, int baseSpeed) {
			_scrollView = scrollView;
			_baseSpeed = baseSpeed;
		}
		
		@SuppressLint("NewApi")
		public void run() {
			while(!_dispose) {
				if(_scrollView.canScrollVertically(0))
					_scrollView.scrollBy(0, 1);
				
				try {
					Thread.sleep(_baseSpeed*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
