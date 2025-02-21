"use client";
import React from "react";
import { useState } from "react";
import SwipeButton from "../components/SwipeButton";

export default function HomePage() {

    /*
    const [id, setId] = useState('');
    const [message, setMessage] = useState('');
    const userId = localStorage.getItem("userId");
    
    const location = localStorage.getItem('location')
    console.log("USERID", userId);
    console.log("LOCATION", location);
    */

    const handleClick = (text) => {
        console.log("you clicked " + text);
    }

    return (
    <div>
        <SwipeButton onClick = {() => handleClick('left')}>Left</SwipeButton>

        <SwipeButton onClick = {() => handleClick('right')}>Right</SwipeButton>
    </div>
    );
    }
