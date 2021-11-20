<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Global -->
  <c:import url="sharedView/global.html"/>
  <link rel="stylesheet" href="./css/bootstrap.css">
  <!-- Local -->
  <link rel="stylesheet" href="./css/profile.css">
<title>Electronic Shop</title>
</head>
<body>
	<c:import url="sharedView/header.jsp"></c:import>
<div class="wrapper">
  <div class="leftbar">
    <div class="folderTab sub">
      <h3>Avatar</h3>
    </div>
    <div class="borderBox">
      <ul class="avatar">
        <img src="https://ichef.bbci.co.uk/news/976/cpsprodpb/16620/production/_91408619_55df76d5-2245-41c1-8031-07a4da3f313f.jpg" Width="179" height="153">
        <li><a href="#">Choose a picture</a></li>
      </ul>
    </div>
  </div>
    <div class="folderTab">
      <h3>Profile</h3>
    </div>
    <div class="borderBox">
      <div class="tabler">
        <h3>Account</h3>
      <table>
        <tbody>
        <tr>
          <td>
            Username
          </td>
          <td>
            bookshop@gmail.com
          </td>
        </tr>
        <tr>
          <td>
            Password
          </td>
          <td>
            <input type="text" value="**********">
          </td>
        </tr>
        <tr>
          <td>
            Phone Number
          </td>
          <td>
            <input type="text" value="0916******">
          </td>
        </tr>
        </tbody>
      </table>
      </div> 
      
      <div class="tabler">
        <h3>Personal Information</h3>
      <table>
        <tbody>
        <tr>
          <td>
            Name
          </td>
          <td>
            <input type="text" value="Kha Banh">
          </td>
        </tr>
        <tr>
          <td>
            Gender
          </td>
          <td>
              <input type="radio" id="gender" value="Male">
              <label for="gender">Male</label>
              <input type="radio" id="gender" value="Female">
              <label for="gender">Female</label>
              <input type="radio" id="gender" value="Other">
              <label for="gender">Other</label>
          </td>
        </tr>
          <tr>
          <td>
            Date of birth
          </td>
          <td>
    <select name="dob-day" id="dob-day">
      <option value="">Day</option>
      <option value="">---</option>
      <option value="01">01</option>
      <option value="02">02</option>
      <option value="03">03</option>
      <option value="04">04</option>
      <option value="05">05</option>
      <option value="06">06</option>
      <option value="07">07</option>
      <option value="08">08</option>
      <option value="09">09</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
      <option value="13">13</option>
      <option value="14">14</option>
      <option value="15">15</option>
      <option value="16">16</option>
      <option value="17">17</option>
      <option value="18">18</option>
      <option value="19">19</option>
      <option value="20">20</option>
      <option value="21">21</option>
      <option value="22">22</option>
      <option value="23">23</option>
      <option value="24">24</option>
      <option value="25">25</option>
      <option value="26">26</option>
      <option value="27">27</option>
      <option value="28">28</option>
      <option value="29">29</option>
      <option value="30">30</option>
      <option value="31">31</option>
    </select>
    <select name="dob-month" id="dob-month">
      <option value="">Month</option>
      <option value="">-----</option>
      <option value="01">January</option>
      <option value="02">February</option>
      <option value="03">March</option>
      <option value="04">April</option>
      <option value="05">May</option>
      <option value="06">June</option>
      <option value="07">July</option>
      <option value="08">August</option>
      <option value="09">September</option>
      <option value="10">October</option>
      <option value="11">November</option>
      <option value="12">December</option>
    </select>
    <select name="dob-year" id="dob-year">
      <option value="">Year</option>
      <option value="">----</option>
      <option value="2012">2012</option>
      <option value="2011">2011</option>
      <option value="2010">2010</option>
      <option value="2009">2009</option>
      <option value="2008">2008</option>
      <option value="2007">2007</option>
      <option value="2006">2006</option>
      <option value="2005">2005</option>
      <option value="2004">2004</option>
      <option value="2003">2003</option>
      <option value="2002">2002</option>
      <option value="2001">2001</option>
      <option value="2000">2000</option>
      <option value="1999">1999</option>
      <option value="1998">1998</option>
      <option value="1997">1997</option>
      <option value="1996">1996</option>
      <option value="1995">1995</option>
      <option value="1994">1994</option>
      <option value="1993">1993</option>
      <option value="1992">1992</option>
      <option value="1991">1991</option>
      <option value="1990">1990</option>
      <option value="1989">1989</option>
      <option value="1988">1988</option>
      <option value="1987">1987</option>
      <option value="1986">1986</option>
      <option value="1985">1985</option>
      <option value="1984">1984</option>
      <option value="1983">1983</option>
      <option value="1982">1982</option>
      <option value="1981">1981</option>
      <option value="1980">1980</option>
      <option value="1979">1979</option>
      <option value="1978">1978</option>
      <option value="1977">1977</option>
      <option value="1976">1976</option>
      <option value="1975">1975</option>
      <option value="1974">1974</option>
      <option value="1973">1973</option>
      <option value="1972">1972</option>
      <option value="1971">1971</option>
      <option value="1970">1970</option>
      <option value="1969">1969</option>
      <option value="1968">1968</option>
      <option value="1967">1967</option>
      <option value="1966">1966</option>
      <option value="1965">1965</option>
      <option value="1964">1964</option>
      <option value="1963">1963</option>
      <option value="1962">1962</option>
      <option value="1961">1961</option>
      <option value="1960">1960</option>
      <option value="1959">1959</option>
      <option value="1958">1958</option>
      <option value="1957">1957</option>
      <option value="1956">1956</option>
      <option value="1955">1955</option>
      <option value="1954">1954</option>
      <option value="1953">1953</option>
      <option value="1952">1952</option>
      <option value="1951">1951</option>
      <option value="1950">1950</option>
    </select>
          </td>
        </tr>
          <tr>
          <td>
            Address
          </td>
          <td>
            <input type="text" size="50" value="1 Vo Van Ngan, Linh Chieu, Thu Duc, HCMC">
          </td>
        </tr>
          <tr>
          <td>
            Country
          </td>
          <td>
            <input type="text" value="Viet Nam">
          </td>
        </tr>
        </tbody>
      </table>
      </div> 
    </div>
  </div>
	<c:import url="sharedView/footer.jsp"></c:import>
</body>
</html>