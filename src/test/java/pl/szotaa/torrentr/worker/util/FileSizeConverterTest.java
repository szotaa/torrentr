package pl.szotaa.torrentr.worker.util;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.szotaa.UnitTest;

import static org.junit.Assert.*;

@Category(UnitTest.class)
public class FileSizeConverterTest {

    @Test
    public void toKb_kibGetsConverted(){
        //given
        double amount = 1;
        String unit = "KiB";

        //when
        double result = FileSizeConverter.toKb(amount, unit);

        //then
        assertEquals(1, result, 0);
    }

    @Test
    public void toKb_mibGetsConverted(){
        //given
        double amount = 1;
        String unit = "MiB";

        //when
        double result = FileSizeConverter.toKb(amount, unit);

        //then
        assertEquals(1024, result, 0);
    }

    @Test
    public void toKb_gibGetsConverted(){
        //given
        double amount = 1;
        String unit = "gib";

        //when
        double result = FileSizeConverter.toKb(amount, unit);

        //then
        assertEquals(1024 * 1024, result, 0);
    }

    @Test
    public void toKb_tbGetsConverted(){
        //given
        double amount = 1;
        String unit = "tb";

        //when
        double result = FileSizeConverter.toKb(amount, unit);

        //then
        assertEquals(1024 * 1024 * 1024, result, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void toKb_unsupportedUnit_illegalArgumentExceptionThrown(){
        //given
        double amount = 1;
        String unit = "";

        //when
        double result = FileSizeConverter.toKb(amount, unit);
    }
}