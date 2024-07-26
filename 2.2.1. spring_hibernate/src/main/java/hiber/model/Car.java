package hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @Id()
    @OneToOne
    @JoinColumn(name = "carUser_id", referencedColumnName = "id")
    private User carUser;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getCarUser() {
        return carUser;
    }

    public void setCarUser(User carUser) {
        this.carUser = carUser;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("model='").append(model).append('\'');
        sb.append(", series=").append(series);
        sb.append('}');
        return sb.toString();
    }
}
