import { Calculator } from "./calculator";

describe('Calculator testing', () => {

    //Step 1: Arrange (initializing variables)
    let calculator:Calculator;
    beforeEach(() => {
        calculator = new Calculator();
        console.log("Before each test case, calculator object is created")
    })
    it('Testing add function',() => {
        //Step 2: Act (performing operation) eg calc.addition(2,3)
        //Step 3: Assert (checking the output) eg expect(calc.addition(2,3)).toBe(5)
        //here we have merged step 2 and 3
        expect(calculator.addition(11,22)).toBe(33)
    })
    it('Testing sub function',() => {
        expect(calculator.subtraction(22,11)).toBe(11)
    })
    it('Testing mul function',() => {
        expect(calculator.multiplication(11,22)).toBe(242)
    })
    it('Testing div function',() => {
        expect(calculator.division(22,11)).toBe(2)
    })
})