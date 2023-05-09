package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferToCardPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement inputAmount = $("[data-test-id='amount'] input");
    private final SelenideElement inputFrom = $("[data-test-id='from'] input");
    private final SelenideElement transferToCard = $(byText("Пополнение карты"));
    private final SelenideElement errorMsg = $("[data-test-id='error-message']");

    public TransferToCardPage() {
        transferToCard.shouldBe(Condition.visible);
    }

    public void makeTransfer(String sumToTransfer, DataHelper.CardInfo cardInfo) {
        inputAmount.setValue(sumToTransfer);
        inputFrom.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String sumToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(sumToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void findErrorMsg(String errorText) {
        errorMsg.shouldHave(Condition.exactText(errorText), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}
