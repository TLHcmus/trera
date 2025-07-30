import Image from 'next/image';

interface AuthLayoutProps {
  children: React.ReactNode;
}

const AuthLayout = ({ children }: AuthLayoutProps) => {
  return (
    <main className="bg-neutral-100 min-h-screen">
      <div className="flex justify-start items-center p-4">
        <Image src="/next.svg" alt="logo" width={100} height={50} />
      </div>

      <div className="flex-1 flex justify-center items-center mt-10 p-4">
        {children}
      </div>
    </main>
  );
};

export default AuthLayout;
