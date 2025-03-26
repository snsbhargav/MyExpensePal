import React from "react";


const Features = () => {
  return (
    <div className="features-container" style={{ width:"100vw",height:"100vh"}}>
      <h2>Why Choose MyExpensePal?</h2>
      <div className="features-list">
          <div className="feature-item">
            <h3>Track Your Expenses</h3>
            <p>Stay on top of your spending with ease. Add, edit, and manage your expenses in seconds.</p>
          </div>
          <div className="feature-item">
            <h3>Analyze Your Spending</h3>
            <p>Gain insights into your financial habits with our intuitive reports and statistics.</p>
          </div>
          <div className="feature-item">
            <h3>Set Budgets</h3>
            <p>Define your budget for various categories and get reminders when you’re near your limit.</p>
          </div>
          <div className="feature-item">
            <h3>Secure and Private</h3>
            <p>Your data is protected with the latest security standards, ensuring your privacy and safety.</p>
          </div>
      </div>
    </div>
  );
};

export default Features;
