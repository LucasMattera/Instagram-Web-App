const Register = () => {
    return (
        <div className="input">
            <input type="text" id="username" name="username" placeholder="Username" required/>
            <input type="text" id="email" name="email" placeholder="Email" required/>
            <input type="text" id="password" name="password" placeholder="Password" required/>
            <input type="text" id="passwordCheck" name="passwordCheck" placeholder="Password Check" required/>
            <button type="button">Register</button>
            <button type="button">Cancel</button>
        </div>
    )
}

export default Register ;