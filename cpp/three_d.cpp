#include <iostream>
using namespace std;

class three_d {
    int x, y, z;  // 3D coordinate
  public:
    three_d() { x = y = z = 0; }
    three_d(int i, int j, int k) { x = i; y = j; z = k; }
    friend three_d operator+ (const three_d op1, three_d op2); // op1 is implied
    three_d operator= (three_d op2); // op1 is implied
    three_d operator++ ();

    void show();
};

// Overload +
three_d operator+(const three_d op1, three_d op2)
{
    three_d temp;

    temp.x = op1.x + op2.x;
    temp.y = op1.y + op2.y;
    temp.z = op1.z + op2.z;
    return temp;
}

three_d three_d::operator=(three_d op2)
{
    x = op2.x;
    y = op2.y;
    z = op2.z;
    return *this;
}

three_d three_d::operator++()
{
    x++;
    y++;
    z++;
    return *this;
}

void three_d::show()
{
    cout << x << ", " << y << ", " << z << endl;
}

int main()
{
    three_d a(1, 2, 3), b(10, 10, 10), c;

    a.show();
    b.show();

    c = a + b;
    c.show();

    ++c;
    c.show();
}
