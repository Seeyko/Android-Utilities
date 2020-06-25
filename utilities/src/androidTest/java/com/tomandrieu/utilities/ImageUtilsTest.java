package com.tomandrieu.utilities;

import android.content.Context;
import android.widget.ImageView;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageUtilsTest {

    @Test
    public void getStringImage() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        String base64result = new StringBuilder("iVBORw0KGgoAAAANSUhEUgAAAH4AAAAhCAYAAAAf+x+qAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAOiSURBVHhe7ZsxaxRBFMfvq0hyZ21pm3SpBCUXK7sDieDJfQRBD2wtg6hJGoONGCIhR4LFpQtikktzWKgBQSWcJKQRnvN25u28nZ3dzc5ecsjMg3+R2f/M7L7fzNvZg9QghJcRwHsaAbynUbt+twZB/imA91QBvKcK4D1VAO+pAnhP5Qx+9vEc3GvbrwU5qj0DD57dsF8bs9zAL6/Dz7/iY/BPD7oB/njU7sDO73OR1BPYXb1m94xRTuBnV3swin4GEFESfvfwRHW0xQDWIt8SDFXLcIf378DuGbaK5Czzdu2nGB122PVsH5yJ+y/yqEjeS7YPwz5/hmLoGOcw3JyBxoLFN0Y5l/rWu74T/CLwb27VoJFIqmhrUn8Nvv9C+DA5y2wRmvF9Sc+d50sspAKgBwS0wPe5A9PzNGaOTOhbczAlclCPn/lyVOlw5wKfwGNi8AFtSoIXIQA2ov4mePrbGG9rIBvR95L3EyHG0nN1oH+q2+Uuo7nlIrSO+SrHx1QIfkLQUZXAo8rC5+CzE2PuJhOgAv9a7eLTHjwxEr12LErzlgRg+qbuaJ+ei+ZIApULTgrHxMBxGwvZv")
                .append("gtpgtBRlcGjWut7Gv6vt9CxeEi5pf6YSjNLKu00Aa0b73AFfltei3b77fRcDQE4SuSOGgN3u8Ungaoxc4DyRVtvks8W5hnE1E1Y+UrQAb5tXy10VHXw7UXY/GGs3MSOSioXPIKJ+vLkC9iqHI/2l6zgsV9uWeXgLffmAn56Ph+8HCs5D1frA9ssox48fWj3XZaqgbdBL1i5PHnoTSkFXiQwPpidwChaBCqxvIQbQBFmfLKm/hZfotQXgOelXoOXPtuzFJ3MWxsM/hV/GruDd4COSu4au8f2nu0e8EpBkNjhbp99PtEOFzHcxrbk4U7Pw9rV+z8TfLz4ZLsu9ekFUkaTgu8G3hE6KrfUC6Dy3WhLqi75GrxoZ5BTgaWd7qvgcw7Hk+9/mtseVKlSXx5mxOeVYk0CvhP45scv6i7Ln0aLwOeW2xgyA4/tloNW/CrhpT0elwWd9HGexOdcOugrQVaqAvAZ54kscfijT4tWzzjlWOrnYOVoAHvvy0GPJRIXQckQAa3T37yvmMv0kchPynqVmD7bST/lUTKfNcsXqQR4VGujD8PBc3h0/wK/AVRUpcNdQ9xcaehB+RL5rF8ydFQl8EH/rwJ4TxXAe6oA3lMF8J4qgPdU4V+oPI0A3tMI4D2NAN7LAPgHfl3o8EmAT9IAAAAASUVORK5CYII=")
                .toString();

        ImageView imageView = new ImageView(appContext);
        imageView.setImageDrawable(appContext.getDrawable(R.drawable.capture));
        String base64iv = ImageUtils.getStringImage(imageView);
        assertEquals(base64result, base64iv);
    }
}