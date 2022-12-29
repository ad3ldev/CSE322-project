import React from "react";
import "./PopUp.scoped.css";
function PopUp(props) {
  return props.trigger ? (
    <div className="popup">
      <div className="popup-inner">
        <button
          class="close-btn"
          aria-label="Close alert"
          type="button"
          onClick={() => props.setTrigger(false)}
        >
          <span aria-hidden="true">&times;</span>
        </button>

        {props.children}
      </div>
    </div>
  ) : (
    ""
  );
}
export default PopUp;
