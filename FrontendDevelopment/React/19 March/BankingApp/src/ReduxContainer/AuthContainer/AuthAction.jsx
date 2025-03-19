import { login_false, login_true } from "./AuthActionTypes";

export const set_login_true = (user) => {
    console.log("Setting login True");
    return{
        type: login_true,
        payload: user
    }
}

export const set_login_false = () => {
    console.log("Setting login False");
    return{
        type: login_false
    }
}