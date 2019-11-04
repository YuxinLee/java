package emp_sys.entity;

/**
 * 1. 员工
 */
public class Employee {

    private int empId;
    private String empName;
    private int dept_id;

    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public int getDept_id() {
        return dept_id;
    }
    public void setDept_id(int deptId) {
        dept_id = deptId;
    }
}
