"use client";
import React from "react";
import { useState } from "react";

export default function HomePage() {

    const [id, setId] = useState('');
    const [message, setMessage] = useState('');
    const userId = localStorage.getItem("userId");
    
    const location = localStorage.getItem('location')
    console.log("USERID", userId);
    console.log("LOCATION", location);

        
    }
