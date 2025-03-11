import { addition } from "./testingFunction"


beforeAll(() => {
    console.log("----------------------Before all test cases----------------------") 
})
afterAll(() => {
    console.log("----------------------After all test cases----------------------")
})
beforeEach(() => {
        console.log("Before each test case")
})
afterEach(() => {
    console.log("After each test case")
})
describe("Calculation tesing", () => {
    it('Testing add function',() => {
        expect(addition(11,22)).toBe(33)
})
})

describe("String tesing", ()=> {
    it('testing String equality using toBe', ()=>{
        let str = "Hello Everybody";
        expect(str).toBe("Hello Everybody");
    })

    it('Testing string equality using "toEqual"',()=>{
        let str = "Hello Everybody";
        expect(str).toEqual("Hello Everybody");
    })

    it('Testing string equality using "toMatch a pattern"',()=>{
        let str = "Hello New Year 2025";
        expect(str).toMatch(/\d+/); //regex expression checking that the string must contain a number
    })
})

describe("Testing  deep eqiality", ()=>{
    it(' in json object', ()=>{
        let obj = {"name":"Raju","age":22,"experience":2};

        expect(obj).toEqual({"name":"Raju","age":22,"experience":2});
    })

    it('in array object', ()=>{
        let obj = [1,2,3,4,5];
        expect(obj).toEqual([1,2,3,4,5]);
    })
})