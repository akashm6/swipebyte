"use client";
import React from "react";
import { useState } from "react";
import { useRouter } from 'next/navigation';

export default function Home() {

  const [message, setMessage] = useState('');
  const router = useRouter();

  const handleGithubLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/github";

  };

  const handleEmailLogin = () => {
    router.push("/auth/login");
  }
  
  const handleRegister = () => {

    router.push("/auth/register")
  }

  return (
    <div>
      <button onClick={handleGithubLogin}>Login with GitHub</button>
      <button onClick = {handleEmailLogin}>Login with Email</button>
      <button onClick = {handleRegister}>Register</button>
    </div>
  );
}
