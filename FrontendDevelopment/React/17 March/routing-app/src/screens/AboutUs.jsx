import React from 'react'
import Header from '../components/Header'
import Title from '../components/Title'
import Footer from '../components/Footer'
import { Link } from 'react-router-dom'

function AboutUs() {
  return (
    <div>
        <Header/>
        <Title msg="About Us"/>
        <div>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Accusantium quo cumque asperiores. Voluptates magni ea provident aliquam, veritatis tempora repellendus! Amet, sequi eos dolorem temporibus voluptatem quo culpa incidunt enim.
        </div>
        <Footer/>
    </div>
  )
}

export default AboutUs