// IMyAidlInterface.aidl
package com.wangliang160616.androidtest.model;

// Declare any non-default types here with import statements
import com.wangliang160616.androidtest.model.student;
interface IMyAidlInterface {

    void setStuInfo(in student stu);
    void setAge(int age);

}
