import React from 'react'
import Header from '../components/Header'
import Footer from '../components/Footer'
import Title from '../components/Title'
import { useNavigate } from 'react-router-dom';

function Home() {
    let  flag = false; //set to true if u want to go to login
    const navigate =useNavigate();

    function goTo(){
        if(flag){
            navigate('/welcome')
        }
        else{
            navigate('/login')
        }
    }
  return (
    <>
    <Header />
    <Title msg="Welcome to Our National Bank"/>
    <p>
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        Welcome to our National Bank
        <input type='button' value='Login' onClick={goTo}></input>
    </p>
    <Footer />
    </>
  )
}

export default Home