package com.advocates.kdba.service;

import com.advocates.kdba.models.BarMembers;
import com.advocates.kdba.repository.BarMembersRepository;
import com.advocates.kdba.response.GenericResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;
import java.util.Date;

@Service
public class BarService {

    @Autowired
    private BarMembersRepository barMembersRepository;

    @Autowired
    private PasswordEncoder encoder;

    public GenericResponse saveBarMember(MultipartFile file,String enrollmentNo,String enrollmentDate,String firstname,
                                         String dob,String gender,
                                         String mobile,String lfNumber,String admissionDate,String remarks,
                                         String cop ) throws IOException {

        GenericResponse<String, String,BarMembers> generics= new GenericResponse<String, String,BarMembers>();
        BarMembers members = barMembersRepository.findByEnrollmentNo(enrollmentNo);

        if(members != null) {

            generics.setMessage("Couldnt Save. Bar Association Member already exists with Enrollment Number "+enrollmentNo);
            return generics;
        }
        else {
            BarMembers barMembers = new BarMembers();
            barMembers.setEnrollmentNo(enrollmentNo);

            Date enrollDt = new Date(enrollmentDate);
            barMembers.setEnrollmentDate(enrollDt.toString());

            barMembers.setFirstname(firstname);

            Date birthDt = new Date(dob);
            barMembers.setDob(birthDt.toString());

            barMembers.setGender(gender);
            barMembers.setMobile(mobile);
            barMembers.setLfNumber(lfNumber);

            Date admissionDt = new Date(admissionDate);
            barMembers.setAdmissionDate(admissionDt.toString());
            barMembers.setRemarks(remarks);
//            barMembers.setAdmin(admin);
            barMembers.setCop(cop);

//            String pass = password;
//            barMembers.setPassword(encoder.encode(pass));

            if(file == null) {
                byte[] data = new byte[] {};
                barMembers.setImage(new Binary(BsonBinarySubType.BINARY, data));
            }
            else {
                barMembers.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            }

            barMembersRepository.save(barMembers);
//            generics.setObject(barMembers);
            generics.setMessage("Bar Member saved Successfully");
            return generics;
        }

//        const [value, setValue] = React.useState(new Date());
//
//        console.log(value.getTime())

    }

    public GenericResponse getMembers() {
        GenericResponse<String, String,List<BarMembers>> generics= new GenericResponse<String, String,List<BarMembers>>();
        List<BarMembers> members = barMembersRepository.findAll();

        generics.setMessage("Displaying List of all KDBA Members");
        generics.setObject(members);

        return generics;
    }

    public GenericResponse updateMembers(BarMembers existingMembers, MultipartFile file, String enrollmentNo,
                                         String enrollmentDate, String firstname, String dob, String gender,
                                         String mobile, String lfNumber, String admissionDate,
                                         String remarks, String cop)throws IOException {

        existingMembers.setEnrollmentNo(enrollmentNo);
        existingMembers.setFirstname(firstname);
        existingMembers.setGender(gender);
        existingMembers.setMobile(mobile);
        existingMembers.setLfNumber(lfNumber);
        existingMembers.setCop(cop);
        existingMembers.setRemarks(remarks);

        Date enrollDt = new Date(enrollmentDate);
        existingMembers.setEnrollmentDate(enrollDt.toString());

        Date birthDt = new Date(dob);
        existingMembers.setDob(birthDt.toString());

        Date admissionDt = new Date(admissionDate);
        existingMembers.setAdmissionDate(admissionDt.toString());


        if(file == null) {
            byte[] existingImage = existingMembers.getImage().getData();
            existingMembers.setImage(new Binary(BsonBinarySubType.BINARY, existingImage));
        }
        else {
            existingMembers.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        }

        barMembersRepository.save(existingMembers);
        GenericResponse<String, String,BarMembers> generics= new GenericResponse<String, String,BarMembers>();
        generics.setMessage("Bar Member Updated Successfully");
        return generics;

    }

    public GenericResponse deleteMembers(String id) {

        barMembersRepository.deleteById(id);

        GenericResponse<String, String,BarMembers> generics= new GenericResponse<String, String,BarMembers>();
        generics.setMessage("Bar Member Deleted successfully");
        return generics;
    }
}


//    String line = "";
//    String splitBy = ",";
//    BufferedReader br = new BufferedReader(new FileReader("testkdba.csv"));
//    FileWriter file = new FileWriter("sample1.csv");
//        while ((line = br.readLine()) != null)   //returns a Boolean value
//                {
//                JSONObject obj = new JSONObject();
//                String[] data = line.split(splitBy);    // use comma as separator
//                obj.put("enrollmentNo", data[0]);
//
//                String enrollmentDate = data[1];
//                if (!"enrollmentdate".equalsIgnoreCase(enrollmentDate)) {
//                if (enrollmentDate.contains("/") ) {
//                Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(enrollmentDate);
//                SimpleDateFormat date = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//                String formattedDate = date.format(date1);
//                obj.put("enrollmentDate", formattedDate);
//
//                } else if (enrollmentDate.contains("-") ) {
//                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(enrollmentDate);
//                SimpleDateFormat date = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//                String formattedDate = date.format(date1);
//                obj.put("enrollmentDate", formattedDate);
//                }
//                else if (enrollmentDate.contains("") ) {
//                Date date3 = new Date();
//                obj.put("enrollmentDate", date3);
//                }
//                else{
//                Date date4 = new Date(data[1]);
//                obj.put("enrollmentDate", date4);
//                }
//
//                }
//
//                obj.put("firstname", data[2]);
//                obj.put("lfNumber", data[3]);
//
//
//                String admissionDate = data[4];
//
//                if (!"admissionDate".equalsIgnoreCase(admissionDate)) {
//                if (admissionDate.contains("/") ) {
//                Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(admissionDate);
//                SimpleDateFormat date = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//                String formattedDate = date.format(date1);
//                obj.put("admissionDate", formattedDate);
//
//                } else if (admissionDate.contains("-") ) {
//                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(admissionDate);
//                SimpleDateFormat date = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//                String formattedDate = date.format(date1);
//                obj.put("admissionDate", formattedDate);
//                }
//                else if (admissionDate.contains("") ) {
//                Date date3 = new Date();
//                obj.put("admissionDate", date3);
//                }
//                else{
//                Date date4 = new Date(data[4]);
//                obj.put("admissionDate", date4);
//                }
//
//                }
//
//                String dob = data[5];
//                if (!"dob".equalsIgnoreCase(dob)) {
//                Date date = new Date();
//                obj.put("dob", date);
//                }
//
//                obj.put("gender", data[6]);
//                obj.put("mobile", data[7]);
//                obj.put("cop", data[8]);
//                //obj.put("copNumber", data[9]);
//
//
//                file.write(obj.toString());
//                file.flush();
//                }