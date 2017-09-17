package com.example.alek.task;

import android.content.Context;

import com.example.alek.task.utils.Utils;
import com.example.alek.task.utils.WrongArgumentsException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test
 */
@RunWith(JUnit4.class)
public class UtilsTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    public Context context;

    @Before
    public void init(){
        context = mock(Context.class);
    }

    @Test
    public void loadJSONFromAssetNullBothTest(){
        exception.expect(WrongArgumentsException.class);
        Utils.loadJSONFromAsset(null,null);
    }

    @Test
    public void loadJSONFromAssetEmptyPathTest(){
        exception.expect(WrongArgumentsException.class);
        Utils.loadJSONFromAsset(context,"");
    }



}