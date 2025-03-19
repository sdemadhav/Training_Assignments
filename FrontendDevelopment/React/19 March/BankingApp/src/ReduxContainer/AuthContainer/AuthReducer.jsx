import { login_false, login_true } from "./AuthActionTypes";

const initialState = {
    isAdminAuthenticated: false,
    user : {}
}
export const AuthReducer = (state=initialState, action) => {
    console.log("Action: "+ action.type);

    switch(action.type){
        case login_true: return {
            ...state,
            isAdminAuthenticated: true,
            user: action.payload
        }
        case login_false:
            return {...state, isAdminAuthenticated: false , user: []}
        default:
            return state
    }
}