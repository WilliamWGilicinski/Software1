import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author William Gilicinski
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        //Update if root is allowed
        if (model.bottom().compareTo(TWO) >= 0
                && model.bottom().compareTo(INT_LIMIT) < 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        //Update if divide is allowed
        if (!model.bottom().isZero()) {
            view.updateDivideAllowed(true);
        } else {
            view.updateDivideAllowed(false);
        }
        //Updates if power is allowed
        if (model.bottom().compareTo(INT_LIMIT) < 0) {
            view.updatePowerAllowed(true);
        } else {
            view.updatePowerAllowed(false);
        }
        //Updates if subtraction is allowed
        if (model.bottom().compareTo(model.top()) <= 0) {
            view.updateSubtractAllowed(true);
        } else {
            view.updateSubtractAllowed(false);
        }

        view.updateTopDisplay(model.top());
        view.updateBottomDisplay(model.bottom());

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        //Copies bottom to top
        this.model.top().copyFrom(this.model.bottom());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        this.model.bottom().add(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        this.model.bottom().subtract(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        this.model.bottom().multiply(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        //Finds remainder and divides top by bottom
        NaturalNumber temp = this.model.top().divide(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        //Sets top to remainder
        this.model.top().transferFrom(temp);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        this.model.top().power(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        this.model.top().root(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        this.model.bottom().multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

}
