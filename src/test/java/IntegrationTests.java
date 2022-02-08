import org.junit.*;

import mmu.coffeeshop.*;
import mmu.coffeeshop.menu.*;

public class IntegrationTests {

    @Test
    public void WhenAddItemThenBasketTotalIsCorrect() {
        Basket basket = new Basket();
        Menu mainMenu = new MainMenu(basket, new NullTextDisplay());
        Menu addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(1);         // Coffee
        addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(2);         // Tea
        
        Assert.assertEquals(348, basket.getTotalPrice());
    }

    @Test
    public void WhenRemoveItemThenItemIsRemoved() {
        Basket basket = new Basket();
        Menu mainMenu = new MainMenu(basket, new NullTextDisplay());
        Menu addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(1);         // Coffee
        addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(2);         // Tea
        Menu removeItemMenu = mainMenu.chooseOption(2);
        mainMenu = removeItemMenu.chooseOption(1);      // Remove coffee
        
        Assert.assertEquals(1, basket.getItemCount());
        Assert.assertEquals("Tea", basket.getItems().get(0).getName());
    }

    @Test
    public void WhenOrderIsCompletedThenBasketIsEmpty() {
        Basket basket = new Basket();
        Menu mainMenu = new MainMenu(basket, new NullTextDisplay());
        Menu addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(1);         // Coffee
        addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(2);         // Tea
        Menu completeOrderMenu = mainMenu.chooseOption(3);
        mainMenu = completeOrderMenu.chooseOption(1);
        
        Assert.assertEquals(0, basket.getItemCount());
        Assert.assertEquals(0, basket.getItems().size());
    }

    @Test
    public void WhenRemoveItemThenCostOfItemIsDeductedFromTotalCostOfBasket() {
        Basket basket = new Basket();
        Menu mainMenu = new MainMenu(basket, new NullTextDisplay());
        Menu addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(1);         // Coffee
        Menu removeItemMenu = mainMenu.chooseOption(2);
        removeItemMenu.chooseOption(1);

        Assert.assertEquals(0, basket.getItemCount());
        Assert.assertEquals(0, basket.getTotalPrice());
    }

    @Test
    public void WhenOrderIsCompletedTheTotalPriceIsReset() {
        Basket basket = new Basket();
        Menu mainMenu = new MainMenu(basket, new NullTextDisplay());
        Menu addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(1);         // Coffee
        addItemMenu = mainMenu.chooseOption(1);
        mainMenu = addItemMenu.chooseOption(2);         // Tea
        Menu completeOrderMenu = mainMenu.chooseOption(3);
        mainMenu = completeOrderMenu.chooseOption(1);
        
        Assert.assertEquals(0, basket.getItemCount());
        Assert.assertEquals(0, basket.getTotalPrice());
    }

    class NullTextDisplay implements TextDisplay {
        public void displayLine(String line) {}
    }

}