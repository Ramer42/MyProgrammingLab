import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class HeroTableModel extends AbstractTableModel {

    String[] columnNames = new String[] { "id", "name", "hp", "damage" };

    // 使用从DAO返回的List作为TableModel的数据
    public List<Hero> heros = new HeroDAO().list();
    public int currPage;
    public int maxPage;
    public int sizeDB;
    public List<Integer> pageEndpoints = new ArrayList<>();

    public void updateInfo() {
        pageEndpoints = new ArrayList<>();
        sizeDB = heros.size();
        maxPage = sizeDB / 6 - 1;
        for (int i = 0; i < maxPage + 1; i++) {
            pageEndpoints.add((i + 1) * 6);
//            System.out.println((i + 1) * 6);
        }
        if (sizeDB % 6 != 0) {
            maxPage++;
            pageEndpoints.add(sizeDB);
        }
    }

    public void setPage(int page) {
        currPage = page;
        System.out.printf("%d, %d\n", page * 6, pageEndpoints.get(page) - page * 6);
        heros = new HeroDAO().list(page * 6, pageEndpoints.get(page) - page * 6);
    }

    // heros.size返回一共有多少行
    public int getRowCount() {
        // TODO Auto-generated method stub
        return heros.size();
    }

    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    // 先通过heros.get(rowIndex)获取行对应的Hero对象
    // 然后根据columnIndex返回对应的属性
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hero h = heros.get(rowIndex);
        if (0 == columnIndex)
            return h.id;
        if (1 == columnIndex)
            return h.name;
        if (2 == columnIndex)
            return h.hp;
        if (3 == columnIndex)
            return h.damage;
        return null;
    }

}