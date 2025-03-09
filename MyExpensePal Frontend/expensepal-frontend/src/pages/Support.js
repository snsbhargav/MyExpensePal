import React from "react";
import "./Support.css";

const Support = () => {
  return (
    <div className="support-container" style={{padding:"10px"}}>
      <h1>Support</h1>
      <p>Welcome to MyExpensePal Support! If you're experiencing any issues or have questions, we're here to help. Below are some of the support options available:</p>

      <h2>Support Options</h2>
      <ul>
        <li><strong>Email Support:</strong> You can contact us via email at praveen.muddunur@myexpensepal.com for any general inquiries, technical issues, or account-related questions.</li>
        <li><strong>Live Chat:</strong> For immediate assistance, you can use our live chat feature on the website. Simply click the chat icon at the bottom right corner to get started with one of our support agents.</li>
        <li><strong>Phone Support:</strong> If you'd prefer to speak with a support representative, you can reach us by phone at (203) 3833951. Available Monday to Friday, 9 AM to 5 PM.</li>
      </ul>

      <h2>Need More Help?</h2>
      <p>If none of the above options resolve your issue, please don't hesitate to reach out to us via any of the available methods, and we'll get back to you as soon as possible.</p>
    </div>
  );
};

export default Support;
