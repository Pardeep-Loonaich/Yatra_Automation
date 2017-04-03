package com.Yatra.Pages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Yatra.Utils.Log;
import com.Yatra.Utils.Utils;

/**
 * ElementLayer page is used to verify each page elements.
 * 
 * We can declare and initialize this class on each page object classes
 */
public class ElementLayer {

    private final WebDriver driver;

    /**
     * Constructor class for ElementLayer, here we initializing the driver for
     * page
     * @param driver -
     */
    public ElementLayer(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 
     * 
     * 
     */
    public boolean verifyPageElements(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such a field present on this page, Please check the expected list values:: " + expEle);
            }
            WebElement element = null;
            try {
                element = ((WebElement) f.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
            if (Utils.waitForElement(driver, element, 2)) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedElements, actual_elements);
    }
    
    /**
     * 
     * 
     * 
     * 
     */
    public boolean verifyPageElementsDisabled(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such a field present on this page, Please check the expected list values:: " + expEle);
            }
            WebElement element = null;
            try {
                element = ((WebElement) f.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
            if (Utils.waitForDisabledElement(driver, element, Utils.maxElementWait)) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedElements, actual_elements);
    }
    
    /**
     * 
     *
     * 
     * 
     */
    public boolean verifyPageElementsChecked(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such field present on this page, Please check the expected list values:: " + expEle);
            }
            WebElement element = null;
            try {
                element = ((WebElement) f.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
            (new WebDriverWait(driver, 5).pollingEvery(250, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class).withMessage("Creat an Event mobel box did not open")).until(ExpectedConditions.visibilityOf(element));
            if (element.isSelected()) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedElements, actual_elements);
    }

    /**
     *
     * 
     * 
     * 
     */
    public boolean verifyPageElementsDoNotExist(List<String> expectedNotToSee, Object obj) throws Exception {
        List<String> nonexisting_elements = new ArrayList<String>();
        for (String expEle : expectedNotToSee) {
            Field f = null;
            WebElement element = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
                element = ((WebElement) f.get(obj));
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such field present on this page, Please check the expected list values:: " + expEle);
            }
            if (!Utils.waitForElement(driver, element, 2)) {
                nonexisting_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedNotToSee, nonexisting_elements);
    }

    /**
     * 
     * 
     * 
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public boolean verifyPageListElements(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such fields present on this page, Please check the expected list values:: " + expEle);
            }
            List<WebElement> elements = null;
            try {
                elements = ((List<WebElement>) f.get(obj));
            } catch (ClassCastException | IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
            if (elements.size() > 0 
                    && Utils.waitForElement(driver, elements.get(0))) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedElements, actual_elements);
    }

    /**
     * 
     * 
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public boolean verifyPageListElementsDoNotExist(List<String> expectedNotToSee, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedNotToSee) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such fields present on this page, Please check the expected list values:: " + expEle);
            }
            List<WebElement> elements = null;
            try {
                elements = ((List<WebElement>) f.get(obj));
            } catch (ClassCastException | IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
            if (elements.size() == 0) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedNotToSee, actual_elements);
    }

    @SuppressWarnings("unchecked")
    public boolean verifyPageListElementsDisabled(List<String> expectedElements, Object obj) throws Exception {
        List<String> actual_elements = new ArrayList<String>();
        for (String expEle : expectedElements) {
            Field f = null;
            try {
                f = obj.getClass().getDeclaredField(expEle);
                f.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e1) {
                throw new Exception("No such a field present on this page, Please check the expected list values:: " + expEle);
            }
            List<WebElement> elements = null;
            try {
                elements = ((List<WebElement>) f.get(obj));
            } catch (ClassCastException |IllegalArgumentException | IllegalAccessException e1) {
                Log.exception(e1);
            }
           if (elements.size() > 0 
                    && Utils.waitForDisabledElement(driver, elements.get(0), Utils.maxElementWait)) {
                actual_elements.add(expEle);
            }
        }
        return Utils.compareTwoList(expectedElements, actual_elements);
    }
}
