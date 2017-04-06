<form class="modal-content animate" id="reg"  enctype="multipart/form-data">
    <div class="container">
        <label><b>Login</b></label>
        <input type="text" placeholder="Enter login" id="input1" name="login" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" id="input2" name="password" required>

        <label><b>Name</b></label>
        <input type="text" placeholder="Enter Name" id="input3" name="name" required>

        <label><b>Surname</b></label>
        <input type="text" placeholder="Enter Surname" id="input4" name="surname" required>

        <label><b>Phone</b></label>
        <input type="text" placeholder="Enter Phone" id="input5" name="phone" required>

        <label><b>Photo</b></label>
        <input type="file" placeholder="Enter Photo" id="input6" name="multipartFile">

        <label><b>Email</b></label>
        <input type="text" placeholder="Enter Email" id="input7" name="email" required>

        <div class="clearfix">
            <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn" id="regButtom">Create a new user</button>
        </div>
    </div>
</form>