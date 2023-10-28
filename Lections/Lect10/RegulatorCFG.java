import lombok.Data;

@Data
@Xml
public class RegulatorCfg {
    private RegulatorInfo regulatorInfo;
    private double minQ;
    private double maxQ;
    private Coefs coefs;
}