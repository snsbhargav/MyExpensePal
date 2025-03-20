import React from "react";
import "../styles/AboutUs.css";

const AboutUs = () => {
  return (
    <div className="about-container">
      <h1>About Us</h1>
      <p>
        Welcome to <strong>MyExpensePal</strong>, an app designed to simplify expense tracking and budgeting.
      </p>

      <h2>Meet the Creators</h2>
      <div className="creator">
        <div>
          <h3>Praveen Muddunur</h3>
        </div>
        <div>
          <h3>Bhargav Siddineni</h3>
        </div>
      </div>
      <h2>Why We Built This App?</h2>
      <p>
        MyExpensePal was created to help users take control of their finances with a simple and intuitive interface.
      </p>
      <h2>Future Plans</h2>
      <p>
        We are working on adding new features such as live chat support, AI-based insights, and more integrations.
      </p>
    </div>
  );
};

export default AboutUs;
