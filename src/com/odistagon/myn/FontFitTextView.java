package com.odistagon.myn;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * from
 * http://stackoverflow.com/questions/2617266/how-to-adjust-text-font-size-to-fit-textview
 */
public class FontFitTextView extends TextView
{

	public FontFitTextView(Context context) {
		super(context);
		initialise();
	}

	public FontFitTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise();
	}

	private void initialise() {
		//max size defaults to the intially specified text size unless it is too small
		maxTextSize = this.getTextSize();
		if (maxTextSize < 11) {
			maxTextSize = 20;
		}
		minTextSize = 10;
	}

	/* Re size the font so the specified text fits in the text box
	 * assuming the text box is the specified width.
	 */
	private void refitText(String text, int nViewWidth) {
		if (nViewWidth <= 0)
			return;

		final float	fdensity = getContext().getResources().getDisplayMetrics().density;
		int			nAvailableWidth = nViewWidth - getPaddingLeft() - getPaddingRight();
		float		fTrySize = maxTextSize;

		Paint	p0 = getPaint();
		p0.setTextSize(fTrySize);
//		float	f0 = p0.measureText(text);
		while ((fTrySize > minTextSize)
				&& (p0.measureText(text) * fdensity > (nAvailableWidth))) {
			fTrySize -= 1;
			if (fTrySize <= minTextSize) {
				fTrySize = minTextSize;
				break;
			}
			p0.setTextSize(fTrySize);
		}

		this.setTextSize(fTrySize);
	}

	@Override
	protected void onTextChanged(final CharSequence text,
			final int start, final int before, final int after) {
		refitText(text.toString(), this.getWidth());
	}

	@Override
	protected void onSizeChanged (int w, int h, int oldw, int oldh) {
		if (w != oldw) {
			refitText(this.getText().toString(), w);
		}
	}

	//Getters and Setters
	public float getMinTextSize() {
		return minTextSize;
	}

	public void setMinTextSize(int minTextSize) {
		this.minTextSize = minTextSize;
	}

	public float getMaxTextSize() {
		return maxTextSize;
	}

	public void setMaxTextSize(int minTextSize) {
		this.maxTextSize = minTextSize;
	}

	//Attributes
	private float minTextSize;
	private float maxTextSize;

}